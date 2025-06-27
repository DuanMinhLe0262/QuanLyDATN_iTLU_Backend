package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.SinhVienHuongDanCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienHuongDanUpdateRequest;
import com.example.backend_itlu.dto.response.ImportError;
import com.example.backend_itlu.dto.response.ImportResult;
import com.example.backend_itlu.dto.response.SinhVienHuongDanResponse;
import com.example.backend_itlu.entity.*;
import com.example.backend_itlu.enums.VaiTroHuongDan;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.SinhVienHuongDanMapper;
import com.example.backend_itlu.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.analysis.function.Sinh;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.backend_itlu.utils.ExcelHelper.getString;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SinhVienHuongDanService {

    SinhVienHuongDanMapper sinhVienHuongDanMapper;
    SinhVienHuongDanRepository sinhVienHuongDanRepo;

    SinhVienRepository sinhVienRepo;
    GiangVienRepository giangVienRepo;

    UserRepository userRepo;


    public SinhVienHuongDanResponse createSinhVienHuongDan(SinhVienHuongDanCreationRequest request) {
        SinhVien sinhVien = sinhVienRepo.findByMaSinhVien(request.getMaSinhVien())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        GiangVien giangVien = giangVienRepo.findByMaGiangVien(request.getMaGiangVien())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        SinhVienHuongDan sinhVienHuongDan = SinhVienHuongDan.builder()
                .giangVien(giangVien)
                .sinhVien(sinhVien)
                .vaiTroHuongDan(request.getVaiTroHuongDan())
                .build();

        return sinhVienHuongDanMapper.toSinhVienHuongDanResponse(sinhVienHuongDanRepo.save(sinhVienHuongDan));
    }

    public ImportResult<SinhVienHuongDanResponse> importFromExcel(MultipartFile file) {
        List<SinhVienHuongDanResponse> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();

        String emailGV = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(emailGV)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        GiangVien giangVien = giangVienRepo.findByUserId(user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    String msv = getString(row, 0).trim();
                    String vaiTroStr = getString(row, 1).trim().toUpperCase();

                    // Kiểm tra
                    if (msv.isBlank() || vaiTroStr.isBlank()) {
                        throw new IllegalArgumentException("Thiếu mã sinh viên hoặc vai trò");
                    }

                    SinhVien sv = sinhVienRepo.findByMaSinhVien(msv)
                            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sinh viên: " + msv));

                    VaiTroHuongDan vaiTro = VaiTroHuongDan.valueOf(vaiTroStr);

                    if (sinhVienHuongDanRepo.countBySinhVienId(sv.getId()) >= 2) {
                        throw new IllegalArgumentException("Sinh viên đã có đủ 2 GVHD");
                    }

                    if (sinhVienHuongDanRepo.existsBySinhVienIdAndGiangVienId(sv.getId(), giangVien.getId())) {
                        throw new IllegalArgumentException("Giảng viên đã hướng dẫn sinh viên này");
                    }

                   SinhVienHuongDan sinhVienHuongDan = SinhVienHuongDan.builder()
                           .giangVien(giangVien)
                           .sinhVien(sv)
                           .vaiTroHuongDan(vaiTro)
                           .build();

                    SinhVienHuongDan saved = sinhVienHuongDanRepo.save(sinhVienHuongDan);
                    successList.add(sinhVienHuongDanMapper.toSinhVienHuongDanResponse(saved));

                } catch (Exception e) {
                    errorList.add(new ImportError(i + 1, e.getMessage()));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Lỗi đọc file: " + e.getMessage());
        }

        return new ImportResult<>(successList, errorList);
    }

    public SinhVienHuongDanResponse updateSinhVienHuongDan(String sinhVienHuongDanId, SinhVienHuongDanUpdateRequest request) {

        SinhVienHuongDan sinhVienHuongDan = sinhVienHuongDanRepo.findById(sinhVienHuongDanId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        sinhVienHuongDanMapper.updateSinhVienHuongDanFromRequest(sinhVienHuongDan, request);
        return sinhVienHuongDanMapper.toSinhVienHuongDanResponse(sinhVienHuongDanRepo.save(sinhVienHuongDan));
    }

    public SinhVienHuongDanResponse getSinhVienHuongDanById(String sinhVienHuongDanId) {

        SinhVienHuongDan sinhVienHuongDan = sinhVienHuongDanRepo.findById(sinhVienHuongDanId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return sinhVienHuongDanMapper.toSinhVienHuongDanResponse(sinhVienHuongDan);
    }

    public List<SinhVienHuongDanResponse> getAllSinhVienHuongDan() {

        List<SinhVienHuongDan> sinhVienHuongDanList = sinhVienHuongDanRepo.findAll();

        return sinhVienHuongDanMapper.toSinhVienHuongDanResponseList(sinhVienHuongDanList);
    }

    public void deleteSinhVienHuongDan(String sinhVienHuongDanId) {
        if(!sinhVienHuongDanRepo.existsById(sinhVienHuongDanId))
            throw new AppException(ErrorCode.USER_NOT_FOUND);

        sinhVienRepo.deleteById(sinhVienHuongDanId);
    }

}
