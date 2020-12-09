package com.example.gpaymotion.data

import com.example.gpaymotion.models.BaseModel

sealed class HomeDataState {
    class Success(val result: List<BaseModel>): HomeDataState()
    class Error(val error: Throwable): HomeDataState()
    object Loading: HomeDataState()
}
