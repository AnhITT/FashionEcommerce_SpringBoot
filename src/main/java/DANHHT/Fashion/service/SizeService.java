package DANHHT.Fashion.service;

import DANHHT.Fashion.model.Size;

import java.util.List;

public interface SizeService {
    List<Size> getAllSize();
    void saveSize(Size size);
    Size getSizeById(long id);
    void deleteSizeById(long id);
}
