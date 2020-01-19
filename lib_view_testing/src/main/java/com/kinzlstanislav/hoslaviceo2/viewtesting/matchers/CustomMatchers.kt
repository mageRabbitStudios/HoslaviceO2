package com.kinzlstanislav.hoslaviceo2.viewtesting.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.kinzlstanislav.hoslaviceo2.viewtesting.FragmentKoinTest
import org.assertj.core.api.Assertions.assertThat
import org.hamcrest.Matcher


/**Matchers that are not included with Barista*/

fun assertViewHolderOfItemAtPosition(
    recyclerView: RecyclerView, position: Int,
    type: Class<out RecyclerView.ViewHolder>) {
    assertThat(recyclerView.findViewHolderForAdapterPosition(position))
            .isExactlyInstanceOf(type)
}

fun assertToolbarTitle(title: String, toolbarId: Int) {
    onView(ViewMatchers.withText(title))
            .check(ViewAssertions.matches(ViewMatchers.withParent(withId(toolbarId))))
}

// good for flipper checks since barista won't work for visibility checks on views inside flipper
fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

fun ViewInteraction.isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

fun ViewInteraction.isInvisible() = getViewAssertion(ViewMatchers.Visibility.INVISIBLE)

private fun getViewAssertion(visibility: ViewMatchers.Visibility): ViewAssertion? {
    return ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
}