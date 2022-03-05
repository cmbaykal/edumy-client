package com.baykal.edumyclient.data.repository

import com.baykal.edumyclient.base.data.BaseRepository
import com.baykal.edumyclient.data.service.EdumyService
import javax.inject.Inject

class ClassroomsRepository @Inject constructor(
    private val service: EdumyService
) : BaseRepository() {



}