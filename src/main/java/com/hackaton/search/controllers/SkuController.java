package com.hackaton.search.controllers;

import com.hackaton.search.model.Sku;
import com.hackaton.search.rest.vo.SkuSearchRequest;
import com.hackaton.search.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/sku")
@Api(tags = { "Search SKU" })
public class SkuController {

	private SkuService skuService;

	public SkuController(SkuService skuService) {
		this.skuService = skuService;
	}

	@ApiOperation(value = "Gets a list of SKUs", response = Sku.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK")})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sku>> getSkus(@ApiParam(value = "General SKU search", required = true) @RequestBody final SkuSearchRequest skuSearchRequest) {

		return new ResponseEntity<List<Sku>>(skuService.findSkusByText(skuSearchRequest.getText()), HttpStatus.OK);
	}

	@ApiOperation(value = "Gets a list of SKUs", response = Sku.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK")})
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sku> putSkus(@ApiParam(value = "Add SKU", required = true) @RequestBody final Sku sku) {

		return new ResponseEntity<Sku>(skuService.addSku(sku), HttpStatus.OK);
	}
}
