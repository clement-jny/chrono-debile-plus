// package com.chronodebileplus.central.stores;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @Configuration
// @RestController
// @RequestMapping("/central")
// public class StoreController {
//     // private final StoreService storeService;

//     // @Value("${myStore.name}")
//     // private String _myStoreName;
//     // @Value("${myStore.url}")
//     // private String _myStoreUrl;

//     // private Store _myStore = new Store(_myStoreName, _myStoreUrl);

//     // public StoreController(StoreService _storeService) {
//     //     this.storeService = _storeService;
//     // }

//     // @GetMapping()
//     // public List<Store> retrieveStore() {
//     //     return this.storeService.retrieveStores();
//     // }

//     // @PostMapping("/register")
//     // public Store registerStore() {
//     //     // Utilise la méthode centralisée pour vérifier l'existence du nom
//     //     if (storeService.storeNameExists(_myStoreName)) {
//     //         throw new IllegalArgumentException("Le nom du magasin est déjà utilisé: " + _myStoreName);
//     //     }
        
//     //     return this.storeService.registerStore(_myStore);
//     // }
// }
