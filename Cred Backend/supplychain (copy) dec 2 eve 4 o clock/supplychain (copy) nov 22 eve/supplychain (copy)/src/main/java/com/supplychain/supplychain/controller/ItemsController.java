package com.supplychain.supplychain.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import org.springframework.util.StringUtils;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.supplychain.supplychain.entity.Item;
import com.supplychain.supplychain.exception.NotFoundException;
import com.supplychain.supplychain.form.ItemForm;
import com.supplychain.supplychain.repository.ItemRepository;
import com.supplychain.supplychain.security.util.SecurityUtil;
import com.supplychain.supplychain.service.ItemService;
import com.supplychain.supplychain.util.FileUtil;
import com.supplychain.supplychain.view.ItemDetailView;
import com.supplychain.supplychain.view.ItemListView;



@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;
    
    @GetMapping
    public Collection<ItemListView> list() {
        return itemService.list();
    }


    @GetMapping("/listall")
    public Collection<Item> listAll() {
        return itemService.listAll();
    }


    @GetMapping("/listbypost")
    public Collection<Item> listByPost() {
        return itemService.listByPost();
    }


    @GetMapping("/listall/{pageNo}/{pageSize}")
    public List<Item> getPaginatedCourse(@PathVariable int pageNo, 
            @PathVariable int pageSize) {

        return itemService.findPaginated(pageNo, pageSize);
    }



    @GetMapping("/listallbysupplier")
    public Collection<Item> listAllBySupplier() {
        return itemService.listAllBySupplier();
    }


    @PostMapping
    public ItemDetailView add(@Valid @RequestBody ItemForm form) {
        return itemService.add(form);
    }

    @GetMapping("/{itemId}")
    public ItemDetailView get(@PathVariable("itemId") Integer itemId) {
        return itemService.get(itemId);
    }

    @PostMapping("/collection")
    public void postArray(@RequestBody List<Integer> arr) {
        itemService.saveCollection(arr);
        System.out.println("%%%%%%%%%%%"+arr);
    }


    @PutMapping("/{itemId}")
    public ItemDetailView update(
            @PathVariable("itemId") Integer itemId,
            @Valid @RequestBody ItemForm form
    ) {
        return itemService.update(itemId, form);
    }

    @DeleteMapping("/{itemId}")
    public void delete(@PathVariable("itemId") Integer itemId) {
        itemService.delete(itemId);
    }

    

    @PostMapping("/save/image/{itemId}")
	public void saveUserProfile(@RequestParam(value="photos" )  MultipartFile multipartFile,
    @PathVariable Integer itemId) throws IOException {

		Item item = itemRepository.findByItemId(itemId);

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		item.setPhotos(fileName);

		itemRepository.save(item);


		FileUtil.saveUserProfile(fileName, multipartFile);

	}



    @PutMapping("/update/image/{itemId}")
	public void updateUserProfile(@RequestParam(value="photos" )  MultipartFile multipartFile,
    @PathVariable Integer itemId) throws IOException {

		Item item = itemRepository.findByItemId(itemId);

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		item.setPhotos(fileName);

		itemRepository.save(item);


		FileUtil.saveUserProfile(fileName, multipartFile);

	}

    

	@GetMapping("/profile")
	public HttpEntity<byte[]> getProfilePic() {

		return itemService.getProfilePic(SecurityUtil.getCurrentUserId());
}


@GetMapping("/filter")
    public Collection<Item> list(@RequestParam(required = false) String itemName,@RequestParam(required = false) String filterItem) {
        return itemService.list(itemName,filterItem);
}


}