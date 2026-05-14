package com.example.nammahomestay.data.repository

import com.example.nammahomestay.domain.model.HomeProfile
import com.example.nammahomestay.domain.model.Inquiry
import com.example.nammahomestay.domain.model.LocalGuide
import com.example.nammahomestay.domain.model.Menu
import com.example.nammahomestay.domain.repository.HomestayRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseHomestayRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomestayRepository {

    private val hostId = "default_host"

    override fun getHomeProfile(): Flow<HomeProfile?> {
        return firestore.collection("hosts").document(hostId).snapshots().map { snapshot ->
            snapshot.toObject(HomeProfile::class.java)
        }
    }

    override suspend fun updateHomeProfile(profile: HomeProfile) {
        firestore.collection("hosts").document(hostId).set(profile).await()
    }

    override fun getTodayMenu(): Flow<Menu?> {
        return firestore.collection("menus")
            .whereEqualTo("isActive", true)
            .snapshots()
            .map { it.documents.firstOrNull()?.toObject(Menu::class.java) }
    }

    override suspend fun updateMenu(menu: Menu) {
        val batch = firestore.batch()
        val oldMenus = firestore.collection("menus").whereEqualTo("isActive", true).get().await()
        for (doc in oldMenus) {
            batch.update(doc.reference, "isActive", false)
        }
        val newMenuRef = firestore.collection("menus").document()
        batch.set(newMenuRef, menu.copy(id = newMenuRef.id))
        batch.commit().await()
    }

    override fun getInquiries(): Flow<List<Inquiry>> {
        return firestore.collection("inquiries")
            .orderBy("timestamp")
            .snapshots()
            .map { it.toObjects(Inquiry::class.java) }
    }

    override fun getLocalGuides(): Flow<List<LocalGuide>> {
        return firestore.collection("locations")
            .snapshots()
            .map { it.toObjects(LocalGuide::class.java) }
    }

    override suspend fun addLocalGuide(guide: LocalGuide) {
        val ref = firestore.collection("locations").document()
        firestore.collection("locations").document(ref.id).set(guide.copy(id = ref.id)).await()
    }
}
