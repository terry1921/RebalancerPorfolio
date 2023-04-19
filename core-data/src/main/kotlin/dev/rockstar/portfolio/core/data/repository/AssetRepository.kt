package dev.rockstar.portfolio.core.data.repository

import androidx.annotation.WorkerThread
import dev.rockstar.portfolio.core.model.Asset
import kotlinx.coroutines.flow.Flow

/** The AssetRepository interface defines functions for fetching, inserting, getting, and updating
 * digital assets with callbacks for handling start, completion, and error cases.
 */
interface AssetRepository {


    /**
     * The function "fetchData" takes three lambda functions as parameters and returns a Flow of a list
     * of Asset objects.
     *
     * @param onStart This is a function that will be called when the data fetching process starts. It
     * can be used to show a loading indicator or perform any other necessary setup tasks.
     * @param onComplete `onComplete` is a lambda function that will be called when the data fetching
     * operation is completed successfully. It does not take any parameters and its return type is
     * `Unit`.
     * @param onError onError is a lambda function that takes a nullable String parameter and returns
     * Unit. It is called when an error occurs during the execution of the fetchData function. The
     * String parameter can be used to pass an error message or description to the caller.
     */
    @WorkerThread
    fun fetchData(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Asset>>

    /**
     * The function takes an asset and three callback functions as parameters and returns a flow of
     * boolean values.
     *
     * @param asset The asset parameter is an object of type Asset that represents the asset to be
     * inserted. It could be an image, video, audio file, or any other type of digital asset.
     * @param onStart `onStart` is a lambda function that will be executed when the asset insertion
     * process starts. It does not take any parameters and does not return anything. It is typically
     * used to update the UI to indicate that the insertion process has started.
     * @param onComplete `onComplete` is a lambda function that will be called when the asset insertion
     * process is completed successfully. It does not take any parameters and returns nothing.
     * @param onError `onError` is a lambda function that takes a nullable `String` parameter and
     * returns `Unit`. It is a callback function that will be called if an error occurs during the
     * execution of the `insertAsset` function. The `String` parameter can be used to provide an error
     * message or description
     */
    @WorkerThread
    fun insertAsset(
        asset: Asset,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean>

    /**
     * The function "getAsset" returns a Flow of Asset objects and takes three lambda functions as
     * parameters for handling the start, completion, and error cases.
     *
     * @param id The id parameter is of type Long and represents the unique identifier of the asset
     * that needs to be retrieved.
     * @param onStart `onStart` is a lambda function that will be executed when the `getAsset` function
     * starts executing. It does not take any parameters and does not return anything. It can be used
     * to perform any necessary setup or initialization before the main logic of the function is
     * executed.
     * @param onComplete `onComplete` is a function that will be called when the operation of getting
     * the asset is completed successfully. It does not take any parameters and its return type is
     * `Unit`.
     * @param onError onError is a lambda function that takes a nullable String parameter and returns
     * Unit. It is used to handle errors that may occur during the execution of the function. If an
     * error occurs, the function will call the onError function and pass the error message as a
     * parameter.
     */
    @WorkerThread
    fun getAsset(
        id: Long,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Asset>

    /**
     * The function "updateAsset" takes an asset object and three lambda functions as parameters and
     * returns a flow of nullable booleans.
     *
     * @param asset The asset parameter is an object of type Asset that represents the asset that needs
     * to be updated. It could be an image, video, audio file, or any other type of digital asset.
     * @param onStart `onStart` is a lambda function that will be executed when the `updateAsset`
     * function starts. It does not take any parameters and does not return anything. It can be used to
     * perform any necessary setup or initialization before the main logic of the function is executed.
     * @param onComplete `onComplete` is a lambda function that will be called when the updateAsset
     * function completes successfully. It does not take any parameters and returns nothing.
     * @param onError onError is a lambda function that takes a nullable String parameter and returns
     * Unit. It is called when an error occurs during the asset update process. The String parameter
     * contains an error message or null if no error message is available.
     */
    @WorkerThread
    fun updateAsset(
        asset: Asset,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<Boolean?>

}