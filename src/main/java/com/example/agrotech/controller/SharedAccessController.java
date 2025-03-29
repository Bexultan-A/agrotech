package com.example.agrotech.controller;

import com.example.agrotech.model.SharedAccess;
import com.example.agrotech.service.SharedAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shared-access")
@RequiredArgsConstructor
public class SharedAccessController {

    private final SharedAccessService sharedAccessService;

    @GetMapping
    public List<SharedAccess> getAllSharedAccess() {
        return sharedAccessService.getAllSharedAccess();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedAccess> getSharedAccessById(@PathVariable Long id) {
        Optional<SharedAccess> sharedAccess = sharedAccessService.getSharedAccessById(id);
        return sharedAccess.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/garden/{gardenId}")
    public List<SharedAccess> getSharedAccessByGarden(@PathVariable Long gardenId) {
        return sharedAccessService.getSharedAccessByGarden(gardenId);
    }

    @GetMapping("/user/{userId}")
    public List<SharedAccess> getSharedAccessByUser(@PathVariable Long userId) {
        return sharedAccessService.getSharedAccessByUser(userId);
    }

    @PostMapping
    public SharedAccess createSharedAccess(@RequestBody SharedAccess sharedAccess) {
        return sharedAccessService.saveSharedAccess(sharedAccess);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSharedAccess(@PathVariable Long id) {
        sharedAccessService.deleteSharedAccess(id);
        return ResponseEntity.noContent().build();
    }
}
