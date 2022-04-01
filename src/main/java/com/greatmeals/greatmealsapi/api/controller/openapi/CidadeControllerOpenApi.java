package com.greatmeals.greatmealsapi.api.controller.openapi;

import com.greatmeals.greatmealsapi.api.exceptionhandler.Problem;
import com.greatmeals.greatmealsapi.api.model.CidadeModel;
import com.greatmeals.greatmealsapi.api.model.input.CidadeInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation("Lista as cidades")
    public List<CidadeModel> listar();

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID da cidade inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @ApiOperation("Busca uma cidade por id")
    public CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    @ApiOperation("Adiciona uma nova cidade")
    public CidadeModel adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                                         CidadeInput cidadeInput);

    @ApiResponses({
            @ApiResponse(code = 200, message = "Cidade atualizada"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @ApiOperation("Atualiza uma cidade por id")
    public CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId,
                                 @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados")
                                         CidadeInput cidadeInput);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluida"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })
    @ApiOperation("Exclui uma cidade por id")
    public void remover(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);

}

