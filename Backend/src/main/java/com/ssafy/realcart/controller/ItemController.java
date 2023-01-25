package com.ssafy.realcart.controller;

import com.ssafy.realcart.data.dto.DeviceDto;
import com.ssafy.realcart.data.dto.ItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @GetMapping()
    public ResponseEntity<List<ItemDto>> getItemList(){
        List<ItemDto> itemList = new ArrayList<>();
        ItemDto itemDto = new ItemDto();
        itemDto.setName("물풍선");
        itemDto.setDesc("2초간 상대 움직임 봉쇄");
        itemDto.setDefaultQuantity(1);
        itemDto.setId(1);
        itemList.add(itemDto);
        return ResponseEntity.status(HttpStatus.OK).body(itemList);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable int id){
        ItemDto itemDto = new ItemDto();
        itemDto.setName("물풍선");
        itemDto.setDesc("2초간 상대 움직임 봉쇄");
        itemDto.setDefaultQuantity(1);
        itemDto.setId(1);
        return ResponseEntity.status(HttpStatus.OK).body(itemDto);
    }

    @PostMapping()
    public ResponseEntity<String> createItem(@RequestBody ItemDto itemDto){

        return ResponseEntity.status(HttpStatus.OK).body("아이템 생성 완료");
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<String> changeItem(@PathVariable("id") int id, @RequestBody ItemDto itemDto){

        return ResponseEntity.status(HttpStatus.OK).body("아이템 수정 완료");
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") int id){

        return ResponseEntity.status(HttpStatus.OK).body("아이템 삭제 완료");
    }
}
