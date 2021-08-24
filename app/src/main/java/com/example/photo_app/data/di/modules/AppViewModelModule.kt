package com.example.photo_app.data.di.modules

import androidx.lifecycle.ViewModel
import com.example.photo_app.data.di.keys.ViewModelKey
import com.example.photo_app.data.viewmodels.photo.PhotoViewModel
import com.example.photo_app.data.viewmodels.photoDetail.PhotoDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    internal abstract fun bindChatViewModel(chatViewModel: PhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailViewModel::class)
    internal abstract fun bindDetailViewModel(detailViewModel: PhotoDetailViewModel): ViewModel
}