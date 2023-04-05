package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.WorkerThread
import dev.rockstar.portfolio.core.model.Group
import kotlinx.coroutines.flow.Flow

/**
 * The GroupRepository interface defines functions for fetching, inserting, getting, and updating
 * groups with callbacks for start, completion, and error.
 */
interface GroupRepository {

    /**
     * This function fetches data and returns a flow of groups, with callbacks for start, completion,
     * and error.
     *
     * @param onStart This is a lambda function that will be executed when the data fetching process
     * starts. It can be used to show a loading indicator or perform any other necessary actions before
     * the data is fetched.
     * @param onComplete `onComplete` is a lambda function that will be called when the data fetching
     * process is complete. It does not take any parameters and is used to indicate that the data
     * fetching process has finished.
     * @param onError onError is a lambda function that takes a nullable String parameter and returns
     * Unit. It is used to handle any errors that may occur during the execution of the fetchData
     * function. If an error occurs, the function will call the onError function and pass the error
     * message as a parameter.
     */
    @WorkerThread
    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Group>>

    /**
     * This function inserts a group and returns a flow of boolean values while executing the provided
     * callbacks for start, completion, and error.
     *
     * @param group The group parameter is an object of the Group class that contains information about
     * a group that needs to be inserted into a database or some other data storage system.
     * @param onStart `onStart` is a lambda function that will be executed when the `insertGroup`
     * function starts executing. It does not take any parameters and does not return anything. It can
     * be used to perform any necessary setup or initialization before the main logic of the function
     * is executed.
     * @param onComplete `onComplete` is a lambda function that will be called when the operation of
     * inserting a `Group` is completed successfully. It does not take any parameters and returns
     * nothing.
     * @param onError The `onError` parameter is a lambda function that takes a nullable `String`
     * parameter and returns `Unit`. It is used to handle any errors that may occur during the
     * execution of the `insertGroup` function. If an error occurs, the `onError` function will be
     * called with a
     */
    @WorkerThread
    fun insertGroup(
        group: Group,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean>

    /**
     * The function "getGroup" returns a Flow of Group objects and takes in three lambda functions for
     * handling the start, completion, and error cases.
     *
     * @param id The id parameter is of type Long and is used to identify the group that needs to be
     * retrieved.
     * @param onStart The `onStart` parameter is a lambda function that will be executed when the
     * `getGroup` function is called and before the actual operation starts. It can be used to perform
     * any necessary setup or initialization before the operation begins.
     * @param onComplete `onComplete` is a function that will be called when the operation of getting a
     * `Group` is completed successfully. It does not take any parameters and its return type is
     * `Unit`.
     * @param onError The `onError` parameter is a lambda function that takes a nullable `String`
     * parameter and returns `Unit`. It is used to handle any errors that may occur during the
     * execution of the `getGroup` function. If an error occurs, the `onError` function will be called
     * with a
     */
    @WorkerThread
    fun getGroup(
        id: Long,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Group>

    /**
     * The function takes in a Group object and three lambda functions, and returns a Flow of nullable
     * Boolean values.
     *
     * @param group The group parameter is of type Group, which is likely a data class or a model
     * representing a group of users or entities in the application. This parameter is likely used to
     * update the group in some way, such as adding or removing members, changing the group name, or
     * updating group settings.
     * @param onStart `onStart` is a lambda function that will be executed when the `updateGroup`
     * function starts. It does not take any parameters and does not return anything. It can be used to
     * perform any necessary setup or initialization before the main logic of the function is executed.
     * @param onComplete `onComplete` is a lambda function that will be called when the updateGroup
     * function completes successfully. It does not take any parameters and returns nothing. It can be
     * used to perform any actions that need to be done after the updateGroup function completes
     * successfully.
     * @param onError The `onError` parameter is a lambda function that takes a nullable `String`
     * parameter and returns `Unit`. It is called when an error occurs during the execution of the
     * `updateGroup` function. The `String` parameter contains the error message or `null` if no error
     * message is available
     */
    @WorkerThread
    fun updateGroup(
        group: Group,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean?>

}
