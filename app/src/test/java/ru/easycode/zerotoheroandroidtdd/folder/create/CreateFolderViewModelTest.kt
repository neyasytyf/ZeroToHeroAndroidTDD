package ru.easycode.zerotoheroandroidtdd.folder.create

import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import ru.easycode.zerotoheroandroidtdd.core.FakeClear
import ru.easycode.zerotoheroandroidtdd.core.FakeClear.Companion.CLEAR
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation
import ru.easycode.zerotoheroandroidtdd.core.FakeNavigation.Companion.NAVIGATE
import ru.easycode.zerotoheroandroidtdd.core.Order
import ru.easycode.zerotoheroandroidtdd.folder.list.FakeAddLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.folder.list.FakeCreateRepository
import ru.easycode.zerotoheroandroidtdd.folder.list.LIVEDATA_CREATE
import ru.easycode.zerotoheroandroidtdd.folder.list.REPOSITORY_CREATE

class CreateFolderViewModelTest {

    private lateinit var order: Order
    private lateinit var liveDataWrapper: FakeAddLiveDataWrapper
    private lateinit var navigation: FakeNavigation
    private lateinit var repository: FakeCreateRepository
    private lateinit var clear: FakeClear
    private lateinit var viewModel: CreateFolderViewModel

    @Before
    fun setup() {
        order = Order()
        clear = FakeClear.Base(order)
        repository = FakeCreateRepository.Base(order, 456789)
        navigation = FakeNavigation.Base(order)
        liveDataWrapper = FakeAddLiveDataWrapper.Base(order)
        viewModel = CreateFolderViewModel(
            repository = repository,
            navigation = navigation,
            dispatcher = Dispatchers.Unconfined,
            dispatcherMain = Dispatchers.Unconfined
        )
    }

    @Test
    fun test() {
        viewModel.createFolder(name = "some folder name")

        repository.checkCreate("some folder name")
        liveDataWrapper.check(FolderUi(id = 456789, title = "some folder name", notesCount = 0))
        navigation.checkScreen(Screen.Pop)
        clear.check(listOf(CreateFolderViewModel::class.java))

        order.check(listOf(REPOSITORY_CREATE, LIVEDATA_CREATE, CLEAR, NAVIGATE))
    }
}

private const val REPOSITORY_CREATE = "FolderRepository.Create#createFolder"
private const val LIVEDATA_CREATE = "FolderListLiveDataWrapper.Create"

private interface FakeCreateRepository : FolderRepository.Create {

    fun checkCreate(name: String)

    class Base(private val order: Order, private var count: Long) : FakeCreateRepository {

        private var actualName = ""

        override fun checkCreate(expectedName: String) {
            assertEquals(expectedName, actualName)
        }

        override suspend fun createFolder(name: String): Long {
            actualName = name
            order.add(REPOSITORY_CREATE)
            return count++
        }
    }
}

private interface FakeAddLiveDataWrapper : FolderListLiveDataWrapper.Create {

    fun check(expected: FolderUi)

    class Base(private val order: Order) : FakeAddLiveDataWrapper {

        private lateinit var actual: FolderUi

        override fun create(folderUi: FolderUi) {
            actual = folderUi
            order.add(LIVEDATA_CREATE)
        }

        override fun check(expected: FolderUi) {
            assertEquals(expected, actual)
        }
    }
}