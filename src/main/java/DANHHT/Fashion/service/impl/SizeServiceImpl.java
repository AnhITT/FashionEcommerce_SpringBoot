package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.model.Size;
import DANHHT.Fashion.repository.ISizeRepository;
import DANHHT.Fashion.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private ISizeRepository iSizeRepository;
    @Override
    public List<Size> getAllSize(){ return iSizeRepository.findAll();}
    @Override
    public void deleteSizeById(long id){  this.iSizeRepository.deleteById(id);
    }
    @Override
    public void saveSize(Size size){
        this.iSizeRepository.save(size);
    }
    @Override
    public Size getSizeById(long id){
        Optional<Size> optional = iSizeRepository.findById(id);
        if(optional.isPresent())
        {
            return optional.get();
        } else {
            throw new RuntimeException("Category not found for id " + id);
        }
    }
}
