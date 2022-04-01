package com.greatmeals.greatmealsapi.api.controller.openapi;

import com.greatmeals.greatmealsapi.api.exceptionhandler.Problem;
import com.greatmeals.greatmealsapi.api.model.GrupoModel;
import com.greatmeals.greatmealsapi.api.model.input.GrupoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    public List<GrupoModel> listar();

    @ApiResponses({
            @ApiResponse(code = 400, message = "ID de grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrado com ID informado", response = Problem.class)
    })
    @ApiOperation("Busca um grupo por ID")
    public GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);

    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado"),
    })
    @ApiOperation("Adiciona um novo grupo")
    public GrupoModel adicionar(@ApiParam(name = "corpo", value = "Representação de um novo grupo") GrupoInput grupoInput);

    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    @ApiOperation("Atualiza um grupo por ID")
    public GrupoModel atualizar(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId,
                                @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados") GrupoInput grupoInput);

    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo excluído"),
            @ApiResponse(code = 404, message = "Grupo não encontrado", response = Problem.class)
    })
    @ApiOperation("Remove um grupo por ID")
    public void remover(@ApiParam(value = "ID de um grupo", example = "1") Long grupoId);
}
