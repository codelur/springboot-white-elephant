package com.games.whiteelephant.service;

import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FirestoreService {

    private final Firestore db = FirestoreClient.getFirestore();

    // Method to add data to Firestore
    public String addDocument(String collection, String documentId, Object data) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collection).document(documentId);
        WriteResult result = docRef.set(data).get(); // Returns a future, we block until result is available
        return result.getUpdateTime().toString();
    }

    // Example method to retrieve data from Firestore
    public <T> T getDocument(String collection, String documentId, Class<T> valueType) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collection).document(documentId);
        return docRef.get().get().toObject(valueType);
    }

}
