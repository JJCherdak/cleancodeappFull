package com.geekbrains.cleancodeapp.view.main

import com.geekbrains.model.data.AppState
import com.geekbrains.model.data.DataModel
import com.geekbrains.repository.Repository
import com.geekbrains.repository.RepositoryLocal
import com.geekbrains.core.viewmodel.Interactor


class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word))
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }
}