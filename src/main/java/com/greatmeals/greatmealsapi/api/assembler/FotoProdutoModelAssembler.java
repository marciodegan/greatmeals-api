package com.greatmeals.greatmealsapi.api.assembler;

import com.greatmeals.greatmealsapi.api.model.FotoProdutoModel;
import com.greatmeals.greatmealsapi.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto fotoProduto) {
        return modelMapper.map(fotoProduto, FotoProdutoModel.class);
    }

//    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
//        return cozinhas.stream()
//                .map(cozinha -> toModel(cozinha))
//                .collect(Collectors.toList());
//    }
}
