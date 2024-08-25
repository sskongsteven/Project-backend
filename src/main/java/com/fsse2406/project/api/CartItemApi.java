package com.fsse2406.project.api;

import com.fsse2406.project.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2406.project.data.cartItem.dto.response.CartItemResponseDto;
import com.fsse2406.project.data.cartItem.dto.response.SuccessResponseDto;
import com.fsse2406.project.data.user.domainObject.request.FirebaseUserData;
import com.fsse2406.project.service.CartItemService;
import com.fsse2406.project.util.JwtUtil;
import jakarta.validation.constraints.Positive;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5174")
public class CartItemApi {
    private final CartItemService cartItemService;

    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public SuccessResponseDto putCartItem(JwtAuthenticationToken jwt,
                                          @PathVariable Integer pid,
                                          @Positive @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwt);

        cartItemService.putCartItem(pid, quantity, firebaseUserData);
        return new SuccessResponseDto();
    }

    @GetMapping
    public List<CartItemResponseDto> getUserCart(JwtAuthenticationToken jwt){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwt);

        List<CartItemResponseDto> getUserCartResponseDtoList = new ArrayList<>();

        for(CartItemResponseData cartItemResponseData : cartItemService.getUserCart(firebaseUserData)){
            getUserCartResponseDtoList.add(new CartItemResponseDto(cartItemResponseData));
        }

        return getUserCartResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResponseDto updateCartItem(JwtAuthenticationToken jwt,
                               @PathVariable Integer pid,
                               @Positive @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwt);

        return new CartItemResponseDto(
                cartItemService.updateCartQuantity(pid, quantity, firebaseUserData));
    }

    @DeleteMapping("/{pid}")
    public SuccessResponseDto removeCartItem(JwtAuthenticationToken jwt,
                                             @PathVariable Integer pid){
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwt);

        cartItemService.removeCartItem(pid, firebaseUserData);
        return new SuccessResponseDto();
    }
}
