package com.shenawynkov.procoretest.domain

import com.shenawynkov.procoretest.domain.models.Image

interface PokemonRepo {
    suspend fun getPokemons(): Result<List<Image>>
}