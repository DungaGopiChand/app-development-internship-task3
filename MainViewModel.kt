package com.example.myapplication2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.model.Post
import com.example.myapplication2.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.await

class MainViewModel : ViewModel() {

    // Holds the list of posts fetched from the API
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getPosts().await()
                _posts.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
