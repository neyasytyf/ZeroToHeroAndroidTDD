package ru.easycode.zerotoheroandroidtdd.folder.details

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.FakeClear
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation.Companion.NAVIGATE
import ru.easycode.zerotoheroandroidtdd.core.Order

class FolderDetailsViewModelTest {

    private lateinit var viewModel: FolderDetailsViewModel
    private lateinit var clear: FakeClear
    private lateinit var folderRepository: FakeFolderRepository
    private lateinit var noteListRepository: FakeNoteListRepository
    private lateinit var folderLiveDataWrapper: FakeFolderLiveDataWrapper
    private lateinit var noteListLiveDataWrapper: FakeNoteListLiveDataWrapper
    private lateinit var navigation: FakeNavigation
    private lateinit var order: Order

    @Before
    fun setup() {
        order = Order()
        clear = FakeClear.Base(order)
        folderRepository = FakeFolderRepository.Base(order)
        noteListRepository = FakeNoteListRepository.Base(order)
        noteListLiveDataWrapper = FakeNoteListLiveDataWrapper.Base(order)
        folderLiveDataWrapper = FakeFolderLiveDataWrapper.Base(order)
        navigation = FakeNavigation.Base(order)

        viewModel = FolderDetailsViewModel(
            folderRepository = folderRepository,
            noteListRepository = noteListRepository,
            noteListLiveDataWrapper = noteListLiveDataWrapper,
            folderLiveDataWrapper = folderLiveDataWrapper,
            navigation = navigation,
            dispatcher = Dispatchers.Unconfined,
            dispatcherMain = Dispatchers.Unconfined
        )
    }

    @Test
    fun test_init() {

        noteListRepository.expectNotes(
            listOf(
                MyNote(id = 1L, title = "first note", folderId = 7L),
                MyNote(id = 2L, title = "second note", folderId = 7L),
            )
        )

        viewModel.init(folderId = 7L)
//        folderLiveDataWrapper.check(FolderUi(id = 7L, title = "fake text", noteCount = 2))
        noteListLiveDataWrapper.check(
            listOf(
                NoteUi(id = 1L, title = "first note", folderId = 7L),
                NoteUi(id = 2L, title = "second note", folderId = 7L),
            )
        )
        noteListRepository.checkFolderId(7L)

        order.check(
            listOf(
//                FOLDER_REPOSITORY_READ,
                NOTES_REPOSITORY_READ,
//                UPDATE_FOLDER_LIVEDATA,
                UPDATE_NOTES_LIVEDATA
            )
        )
    }

    @Test
    fun test_create_note() {
        viewModel.createNote()
        navigation.checkScreen(CreateNoteScreen(folderId = 9L))
        order.check(listOf(NAVIGATE))
    }

    @Test
    fun test_edit_note() {
        viewModel.editNote
        navigation.checkScreen(EditNoteScreen(folderId = 9L))
        order.check(listOf(NAVIGATE))
    }

    @Test
    fun test_edit_folder() {
        viewModel.editFolder()
        navigation.checkScreen(EditFolderScreen(folderId = 9L))
        order.check(listOf(NAVIGATE))
    }

    @Test
    fun test_coneback() {
        viewModel.comeback()
        clear.check(listOf(FolderDetailsViewModel::class.java))
        order.check(listOf(FakeClear.CLEAR))
    }
}

//private const val FOLDER_REPOSITORY_READ = "FolderRepository.Read#folder"
private const val NOTES_REPOSITORY_READ = "NoteListRepository.ReadList#noteList"
private const val UPDATE_NOTES_LIVEDATA = "NoteListLiveDataWrapper.UpdateListAndRead#update"

//private interface FakeFolderRepository : FolderRepository.Read {
//
//    class Base(private val order: Order) : FakeFolderRepository {
//
//        override suspend fun folder(id: Long): Folder {
//            order.add(FOLDER_REPOSITORY_READ)
//            return Folder(id = 7L, title = "fale text", notesCont = 2)
//        }
//    }
//}

private interface FakeNoteListRepository : NoteListRepository.ReadList {

    fun expectNotes(list: List<MyNote>)
    fun checkFolderId(expected: Long)

    class Base(private val order: Order) : FakeNoteListRepository {

        private val actual = mutableListOf<MyNote>()
        private var actualFolderId: Long = -1

        override fun expectNotes(list: List<MyNote>) {
            actual.clear()
            actual.addAll(list)
        }

        override fun checkFolderId(expected: Long) {
            assertEquals(expected, actualFolderId)
        }

        override suspend fun noteList(folderId: Long): List<MyNote> {
            actualFolderId = folderId
            order.add(NOTES_REPOSITORY_READ)
            return actual
        }
    }
}

private interface FakeNoteListLiveDataWrapper : NoteListLiveDataWrapper.UpdateListAndRead {

    fun check(expected: List<NoteUi>)

    class Base(private val order: Order) : FakeNoteListLiveDataWrapper {

        private val actual = mutableListOf<NoteUi>()

        override fun update(notes: List<NoteUi>) {
            actual.clear()
            actual.addAll(notes)
            order.add(UPDATE_NOTES_LIVEDATA)
        }

        override fun check(expected: List<NoteUi>) {
            assertEquals(expected, actual)
        }
    }
}