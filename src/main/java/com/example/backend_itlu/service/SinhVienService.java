package com.example.backend_itlu.service;

import com.example.backend_itlu.dto.request.SinhVienCreationRequest;
import com.example.backend_itlu.dto.request.SinhVienUpdateRequest;
import com.example.backend_itlu.dto.response.ImportError;
import com.example.backend_itlu.dto.response.ImportResult;
import com.example.backend_itlu.dto.response.SinhVienResponse;
import com.example.backend_itlu.entity.DotDoAn;
import com.example.backend_itlu.entity.Lop;
import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.entity.User;
import com.example.backend_itlu.enums.Dot;
import com.example.backend_itlu.enums.Role;
import com.example.backend_itlu.exception.AppException;
import com.example.backend_itlu.exception.ErrorCode;
import com.example.backend_itlu.mapper.SinhVienMapper;
import com.example.backend_itlu.repository.DotDoAnRepository;
import com.example.backend_itlu.repository.LopRepository;
import com.example.backend_itlu.repository.SinhVienRepository;
import com.example.backend_itlu.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.backend_itlu.utils.ExcelHelper.getString;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SinhVienService {

    LopRepository lopRepository;
    SinhVienMapper sinhVienMapper;
    SinhVienRepository sinhVienRepo;
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    DotDoAnRepository dotDoAnRepo;


    public SinhVienResponse createSinhVien(SinhVienCreationRequest request) {

        DotDoAn dotDoAn = dotDoAnRepo.findById(request.getDotId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Lop lop = lopRepository.findByTenLop(request.getLopId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode("Tlu@12345"))
                .roles(Set.of(Role.SINHVIEN))
                .build();

        user = userRepository.save(user);

        SinhVien sinhVien = sinhVienMapper.toSinhVien(request);
        sinhVien.setLop(lop);
        sinhVien.setUser(user);
        sinhVien.setDotDoAn(dotDoAn);
        return sinhVienMapper.toSinhVienResponse(sinhVienRepo.save(sinhVien));
    }

    public ImportResult<SinhVienResponse> importFromExcel(MultipartFile file) {

        List<SinhVienResponse> successList = new ArrayList<>();
        List<ImportError> errorList = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    String trangThai = getString(row, 8).trim().toLowerCase();

                    if (!trangThai.equals("đủ đk") && !trangThai.equals("du dk")) {
                        throw new IllegalArgumentException("Sinh viên không đủ điều kiện");
                    }

                    String msv = getString(row, 3);
                    if (msv.isBlank()) {
                        throw new IllegalArgumentException("MSV không được để trống");
                    }

                    if (sinhVienRepo.existsByMaSinhVien(msv)) {
                        throw new IllegalArgumentException("MSV đã tồn tại: " + msv);
                    }

                    String tenLop = getString(row, 7).trim().toUpperCase();
                    Lop lop = lopRepository.findByTenLop(tenLop)
                            .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

                    DotDoAn dotDoAn = dotDoAnRepo.findDotHienTai(LocalDate.now())
                            .or(() -> dotDoAnRepo.findDotGanNhat(LocalDate.now()))
                            .orElseThrow(() -> new AppException(ErrorCode.DOT_DO_AN_NOT_FOUND));

                    SinhVien sv = SinhVien.builder()
                            .maSinhVien(msv)
                            .hoDem(getString(row, 5))
                            .ten(getString(row, 6))
                            .lop(lop)
                            .dotDoAn(dotDoAn)
                            .build();

                    SinhVienResponse response = sinhVienMapper.toSinhVienResponse(sinhVienRepo.save(sv));

                    successList.add(response);

                } catch (Exception e) {
                    errorList.add(new ImportError(i + 1, e.getMessage()));
                }

            }

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file Excel: " + e.getMessage());
        }

        return new ImportResult<>(successList, errorList);
    }

    public SinhVienResponse updateSinhVien(String sinhVienId, SinhVienUpdateRequest request) {

        SinhVien sinhVien = sinhVienRepo.findById(sinhVienId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        sinhVienMapper.updateSinhVienFromRequest(sinhVien, request);
        return sinhVienMapper.toSinhVienResponse(sinhVienRepo.save(sinhVien));
    }

    public SinhVienResponse getSinhVienById(String sinhVienId) {

        SinhVien sinhVien = sinhVienRepo.findById(sinhVienId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return sinhVienMapper.toSinhVienResponse(sinhVien);
    }

    public List<SinhVienResponse> getAllSinhVien() {

        List<SinhVien> sinhVienList = sinhVienRepo.findAll();

        return sinhVienMapper.toSinhVienResponseList(sinhVienList);
    }

    public void deleteSinhVien(String sinhVienId) {
        if(!sinhVienRepo.existsById(sinhVienId))
            throw new AppException(ErrorCode.USER_NOT_FOUND);

        sinhVienRepo.deleteById(sinhVienId);
    }

}
