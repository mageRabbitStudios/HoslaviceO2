package com.kinzlstanislav.hoslaviceo2.viewtesting

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import com.kinzlstanislav.hoslaviceo2.viewtesting.helpers.InstrumentationTestsHelper
import com.kinzlstanislav.hoslaviceo2.viewtesting.matchers.isGone
import com.kinzlstanislav.hoslaviceo2.viewtesting.matchers.isInvisible
import com.kinzlstanislav.hoslaviceo2.viewtesting.matchers.isVisible
import org.junit.Assert

fun FragmentKoinTest<*>.isViewGone(resourceId: Int) {
    Espresso.onView(ViewMatchers.withId(resourceId)).isGone()
}

fun FragmentKoinTest<*>.isViewVisible(resourceId: Int) {
    Espresso.onView(ViewMatchers.withId(resourceId)).isVisible()
}

fun FragmentKoinTest<*>.isViewInvisible(resourceId: Int) {
    Espresso.onView(ViewMatchers.withId(resourceId)).isInvisible()
}

/**
 * Great for asserting whenever your view is in the hierarchy or not
 * */
fun FragmentKoinTest<*>.printViewHierarchy() {
    InstrumentationTestsHelper.printViewHierarchy(subject.requireActivity())
}

fun FragmentKoinTest<*>.getResString(@StringRes id: Int, args: Any? = null): String
        = targetContext.resources.getString(id, args)

fun FragmentKoinTest<*>.getResInteger(@IntegerRes id: Int) = targetContext.resources.getInteger(id)

fun FragmentKoinTest<*>.getResColor(@ColorRes id: Int) = ContextCompat.getColor(targetContext, id)

fun FragmentKoinTest<*>.getResDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(targetContext, id)

fun FragmentKoinTest<*>.getViewFromActivityById(id: Int): View? {
    val activity = subject.requireActivity()
    activity.let {
        it.findViewById<View>(id)?.let { view ->
            return view
        } ?: run {
            Assert.fail("View is null and so no visibility check can take place on it")
        }
    }
    return null
}