package com.xperiencelabs.noteit.di

import android.app.Application
import androidx.room.Room
import com.xperiencelabs.noteit.noteFeatures.data.data_source.NoteDatabase
import com.xperiencelabs.noteit.noteFeatures.data.repository.NoteRepoImpl
import com.xperiencelabs.noteit.noteFeatures.domain.repository.NoteRepository
import com.xperiencelabs.noteit.noteFeatures.domain.use_case.AddNote
import com.xperiencelabs.noteit.noteFeatures.domain.use_case.DeleteNote
import com.xperiencelabs.noteit.noteFeatures.domain.use_case.GetNoteUseCase
import com.xperiencelabs.noteit.noteFeatures.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app:Application):NoteDatabase{
        return Room.databaseBuilder(
            app,NoteDatabase::class.java,NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepositoryDao(db:NoteDatabase):NoteRepository{
        return NoteRepoImpl(db.noteDao)
    }
    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotes = GetNoteUseCase(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository)
        )
    }

}
