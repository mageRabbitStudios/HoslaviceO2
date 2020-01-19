package com.kinzlstanislav.hoslaviceo2.list.view

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kinzlstanislav.hoslaviceo2.architecture.repository.model.User
import com.kinzlstanislav.hoslaviceo2.base.extensions.set
import com.kinzlstanislav.hoslaviceo2.base.imageloading.GlideImageLoader
import com.kinzlstanislav.hoslaviceo2.list.R
import com.kinzlstanislav.hoslaviceo2.list.view.adapter.UserViewHolder
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState
import com.kinzlstanislav.hoslaviceo2.list.viewmodel.ListViewModel.ListState.*
import com.kinzlstanislav.hoslaviceo2.viewtesting.*
import com.kinzlstanislav.hoslaviceo2.viewtesting.matchers.assertViewHolderOfItemAtPosition
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertDisplayedAtPosition
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.interaction.BaristaSwipeRefreshInteractions.refresh
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.android.synthetic.main.fragment_list.*
import org.junit.Test
import org.robolectric.annotation.LooperMode

class FragmentListTest : FragmentKoinTest<FragmentList>() {

    private companion object {
        val SOME_USERS = listOf(
            User(0, "Thomas Franc", "123", false, "url"),
            User(0, "Stanislav Kinzl", "321", false, "url"),
            User(0, "Android Engineer", "+444", false, "url"),
            User(0, "Lost Grandma", "112", true, "url")
        )
    }

    override val fragmentInstance = FragmentList()

    private val mockViewModel = mockk<ListViewModel>(relaxed = true)
    private val subjectVMState = MutableLiveData<ListState>()

    override fun setup() {
        super.setup()
        mockKoinForFragment {
            single { mockViewModel }
            single<GlideImageLoader> { mockk(relaxed = true) }
        }

        every { mockViewModel.state } returns subjectVMState
        launchFragment()
    }

    @Test
    fun fragmentFlow() {

        // states
        subjectVMState.set(LoadingUsers)
        assertIsViewVisible(R.id.users_list_loader)

        subjectVMState.set(NetworkError)
        assertIsViewGone(R.id.users_list_loader)
        assertIsViewVisible(R.id.users_list_network_error)
        assertHasText(R.id.users_list_network_error, "Network Error")

        subjectVMState.set(GenericError)
        assertIsViewGone(R.id.users_list_network_error)
        assertIsViewVisible(R.id.users_list_generic_error)
        assertHasText(R.id.users_list_generic_error, "Generic Error")

        subjectVMState.set(UsersLoadedOffline(SOME_USERS))
        assertIsViewGone(R.id.users_list_generic_error)
        assertIsViewVisible(R.id.users_list_recycler_view)
        assertListWithAllMembersIsDisplayedCorrectly()

        subjectVMState.set(UsersLoaded(SOME_USERS))
        assertListWithAllMembersIsDisplayedCorrectly()

        // refreshing (with robolectric espresso actions won't work unfortunately)
        subject.refreshListener.onRefresh()
        verify {
            mockViewModel.getUsers()
        }

        // removing user from list with swipe, Stanislav Kinzl should disappear
        onView(withId(R.id.users_list_recycler_view))
            .perform(actionOnItemAtPosition<UserViewHolder>(1, swipeLeft()))

        assertListItemCount(R.id.users_list_recycler_view, 3)
        assertUserItem(0, "Thomas Franc", "123", false)
        assertUserItem(1, "Android Engineer","+444",false)
        assertUserItem(2, "Lost Grandma","112",true)

        verify {
            mockViewModel.removeUserFromLocalDb(SOME_USERS[1])
        }
    }

    private fun assertListWithAllMembersIsDisplayedCorrectly() {
        assertListItemCount(R.id.users_list_recycler_view, 4)
        assertUserItem(0, "Thomas Franc", "123", false)
        assertUserItem(1, "Stanislav Kinzl","321",false)
        assertUserItem(2, "Android Engineer","+444",false)
        assertUserItem(3, "Lost Grandma","112",true)
    }

    private fun assertUserItem(
        position: Int,
        name: String,
        phoneNumber: String,
        isMaster: Boolean
    ) {
        assertDisplayedAtPosition(R.id.users_list_recycler_view, position, R.id.item_user_name, name)
        assertDisplayedAtPosition(R.id.users_list_recycler_view, position, R.id.item_user_phone_number, phoneNumber)
        assertDisplayedAtPosition(R.id.users_list_recycler_view, position, R.id.item_user_is_master,
            if(isMaster) "Je správce" else "Není správce")
        assertViewHolderOfItemAtPosition(
            getViewFromActivityById(R.id.users_list_recycler_view) as RecyclerView,
            position,
            UserViewHolder::class.java
        )
    }
}