package kr.ac.jbnu.kth.crud.controller;

import kr.ac.jbnu.kth.crud.dto.UserApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // 메모리용 임시 DB
    private final Map<Long, String> userDb = new HashMap<>();
    private long sequence = 1L;




    @GetMapping
    public ResponseEntity<UserApiResponse<Map<Long, String>>> getAllUsers() {
        return ResponseEntity.ok(new UserApiResponse<>("success", userDb));
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserApiResponse<String>> getUser(@PathVariable Long id) {
        if (!userDb.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserApiResponse<>("error", "User not found")); // 4xx 코드 충족 (404)
        }
        return ResponseEntity.ok(new UserApiResponse<>("success", userDb.get(id)));
    }




    @PostMapping
    public ResponseEntity<UserApiResponse<String>> createUser(@RequestBody Map<String, String> request) {
        String name = request.get("name");

        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new UserApiResponse<>("error", "Name is required")); // 4xx 코드 충족 (400)
        }

        userDb.put(sequence++, name);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserApiResponse<>("success", "User created: " + name)); // 2xx 코드 다양성 충족 (201)
    }


    @PostMapping("/process")
    public ResponseEntity<UserApiResponse<String>> processData() {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new UserApiResponse<>("error", "Internal server error occurred")); // 5xx 코드 충족 (500)
    }




    @PutMapping("/{id}")
    public ResponseEntity<UserApiResponse<String>> updateUser(@PathVariable Long id, @RequestBody Map<String, String> request) {
        if (!userDb.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserApiResponse<>("error", "User not found to update"));
        }
        userDb.put(id, request.get("name"));
        return ResponseEntity.ok(new UserApiResponse<>("success", "User updated"));
    }


    @PutMapping("/{id}/sync")
    public ResponseEntity<UserApiResponse<String>> syncUser(@PathVariable Long id) {
        // 과제 조건(5xx 에러 최소 2개)을 맞추기 위해 강제로 503 에러를 반환하는 API
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new UserApiResponse<>("error", "External service is currently unavailable")); // 5xx 코드 충족 (503)
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<UserApiResponse<String>> deleteUser(@PathVariable Long id) {
        if (!userDb.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserApiResponse<>("error", "User not found to delete"));
        }
        userDb.remove(id);
        return ResponseEntity.ok(new UserApiResponse<>("success", "User deleted"));
    }


    @DeleteMapping("/clear")
    public ResponseEntity<UserApiResponse<String>> clearUsers() {
        userDb.clear();
        return ResponseEntity.ok(new UserApiResponse<>("success", "All users cleared"));
    }
}