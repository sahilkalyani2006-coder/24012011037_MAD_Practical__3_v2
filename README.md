# MAD Practical-3 : Implicit & Explicit Intent in Android

## AIM

Create an Android application which demonstrates **Implicit Intent** and **Explicit Intent**.

## Objectives

The application demonstrates the following operations:

1. Make a call to a specific number.
2. Open a specific URL.
3. Open the Call Log.
4. Open the Gallery.
5. Set an Alarm.
6. Open the Camera.
7. Open Login Activity using Explicit Intent.

---

## Study

### 1. Intent

An **Intent** is a messaging object used to request an action from another Android component.

Example:

```kotlin
val intent = Intent(this, LoginActivity::class.java)
startActivity(intent)
```

### 2. Types of Intent

#### Implicit Intent

An implicit intent does not specify a particular component. It specifies an action that another application can handle.

Example:

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.google.com")
startActivity(intent)
```

#### Explicit Intent

An explicit intent specifies the exact Activity or component that should be opened.

Example:

```kotlin
val intent = Intent(this, LoginActivity::class.java)
startActivity(intent)
```

---

## 3. Types of Intent Actions

Some commonly used Intent actions are:

| Intent Action                 | Purpose                          |
| ----------------------------- | -------------------------------- |
| `Intent.ACTION_DIAL`          | Opens the phone dialer           |
| `Intent.ACTION_VIEW`          | Opens a URL or other content     |
| `Intent.ACTION_CALL`          | Directly makes a phone call      |
| `Intent.ACTION_SET_ALARM`     | Sets an alarm                    |
| `Intent.ACTION_IMAGE_CAPTURE` | Opens the camera                 |
| `Intent.ACTION_PICK`          | Selects an item such as an image |

---

## 4. `Intent.setData()`

`setData()` is used to specify the data on which an Intent should operate.

Example:

```kotlin
intent.data = Uri.parse("tel:9876543210")
```

It can also be used for opening a URL:

```kotlin
intent.data = Uri.parse("https://www.google.com")
```

---

## 5. `Intent.setType()`

`setType()` specifies the MIME type of the data.

Example:

```kotlin
intent.type = "image/*"
```

This can be used when selecting images from the Gallery.

---

## 6. Button

A **Button** is a UI component that performs an action when the user clicks it.

Example:

```xml
<Button
    android:id="@+id/btnGallery"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Open Gallery" />
```
---

## 7. `startActivity()`

`startActivity()` starts another Activity using an Intent.

Example:

```kotlin
startActivity(intent)
```

---

## 8. ActivityResultContracts

`ActivityResultContracts` provides predefined contracts for launching activities and receiving results.

For selecting an image:

```kotlin
val galleryLauncher =
    registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // Use selected image
    }

galleryLauncher.launch("image/*")
```

---

## 9. Permission in Manifest

Some Android operations require permissions to be declared in `AndroidManifest.xml`.

For making a phone call:

```xml
<uses-permission android:name="android.permission.CALL_PHONE" />
```

---

## 10. `ContextCompat.checkSelfPermission()`

This method checks whether an application has a particular permission.

Example:

```kotlin
ContextCompat.checkSelfPermission(
    this,
    Manifest.permission.CALL_PHONE
)
```

---

## 11. `ActivityCompat.requestPermissions()`

This method requests permission from the user at runtime.

Example:

```kotlin
ActivityCompat.requestPermissions(
    this,
    arrayOf(Manifest.permission.CALL_PHONE),
    101
)
```

---

## 12. `Uri.parse()`

`Uri.parse()` converts a String into a URI object.

Example:

```kotlin
val uri = Uri.parse("https://www.google.com")
```

For a telephone number:

```kotlin
val uri = Uri.parse("tel:9876543210")
```

---

## 13. `ContactsContract.Contacts.CONTENT_TYPE`

This represents the content type used for contacts.

Example:

```kotlin
intent.type = ContactsContract.Contacts.CONTENT_TYPE
```

---

## 14. `CallLog.Calls.CONTENT_TYPE`

This represents the content type associated with the device's call log.

Example:

```kotlin
intent.type = CallLog.Calls.CONTENT_TYPE
```

---

## 15. `"image/*"`

`image/*` represents image MIME types.

It can be used to select images from the Gallery:

```kotlin
intent.type = "image/*"
```

---

## 16. `"tel:"`

The `tel:` URI scheme is used for telephone numbers.

Example:

```kotlin
intent.data = Uri.parse("tel:9876543210")
```

---

# Application Features

The application contains seven buttons:

* **Make Call** – Opens the dialer for a specific number.
* **Open URL** – Opens a specific website.
* **Call Log** – Opens the device Call Log.
* **Gallery** – Opens the Gallery to select an image.
* **Set Alarm** – Opens the alarm interface.
* **Camera** – Opens the device camera.
* **Login Activity** – Opens a separate Login Activity using Explicit Intent.

---

# Important Intent Examples

### 1. Make Call

```kotlin
val intent = Intent(Intent.ACTION_DIAL)
intent.data = Uri.parse("tel:9876543210")
startActivity(intent)
```

`ACTION_DIAL` opens the dialer with the number entered.

For direct calling, `ACTION_CALL` requires the `CALL_PHONE` permission.

---

### 2. Open Specific URL

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.google.com")
startActivity(intent)
```

---

### 3. Open Call Log

```kotlin
val intent = Intent(Intent.ACTION_VIEW)
intent.type = CallLog.Calls.CONTENT_TYPE
startActivity(intent)
```

---

### 4. Open Gallery

```kotlin
val intent = Intent(Intent.ACTION_PICK)
intent.type = "image/*"
startActivity(intent)
```

Alternatively, using `ActivityResultContracts`:

```kotlin
val galleryLauncher =
    registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        // Selected image URI
    }

galleryLauncher.launch("image/*")
```

---

### 5. Set Alarm

```kotlin
val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
    putExtra(AlarmClock.EXTRA_HOUR, 7)
    putExtra(AlarmClock.EXTRA_MINUTES, 0)
}
startActivity(intent)
```

Required import:

```kotlin
import android.provider.AlarmClock
```

---

### 6. Open Camera

```kotlin
val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
startActivity(intent)
```

Required import:

```kotlin
import android.provider.MediaStore
```

---

### 7. Open Login Activity

```kotlin
val intent = Intent(this, LoginActivity::class.java)
startActivity(intent)
```

This is an example of an **Explicit Intent**.

---

# Add Drawable Resource

To add an image or icon to the Android project:

1. Right-click the `res` folder.
2. Select **New → Android Resource Directory** if required.
3. Select resource type **drawable**.
4. Add the image/resource to the `drawable` folder.
5. Use it in XML:

```xml
android:src="@drawable/my_image"
```

Drawable resources can be used for application icons, backgrounds, images, and other UI elements.

---

# Add Activity in Android Project

To add a new Activity:

1. Right-click the application package.
2. Select **New → Activity → Empty Views Activity**.
3. Enter the Activity name, for example `LoginActivity`.
4. Click **Finish**.
5. Android Studio creates the Activity and its XML layout.
6. Open it using Explicit Intent:

```kotlin
val intent = Intent(this, LoginActivity::class.java)
startActivity(intent)
```

---

# Output
<img width="333" height="595" alt="image" src="https://github.com/user-attachments/assets/d3938dab-6d44-4aac-bae9-7a987820e5d8" />
  <img width="333" height="595" alt="image" src="https://github.com/user-attachments/assets/84531f95-c190-4e84-96ab-c0d82ac3f3e0" />





# Conclusion

The Android application successfully demonstrates **Implicit Intent** and **Explicit Intent**. It shows how Android applications can communicate with system applications such as the Dialer, Browser, Call Log, Gallery, Alarm, and Camera. It also demonstrates opening a custom `LoginActivity` using Explicit Intent, along with runtime permissions, `Uri.parse()`, `setData()`, `setType()`, `startActivity()`, and `ActivityResultContracts`.

---
