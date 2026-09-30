package com.shenawynkov.procoretest.data.repo

import com.shenawynkov.procoretest.domain.PokemonRepo
import com.shenawynkov.procoretest.domain.models.Image
import com.shenawynkov.procoretest.networking.ApiService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepoImpl(
    val apiService: ApiService,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonRepo {
    override suspend fun getPokemons(): Result<List<Image>> = withContext(dispatcher) {
        return@withContext try {
            val list = apiService.getPokemons().data.map { Image(it.images.large) }
            Result.success(list)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}