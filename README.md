# Password Manager App

## Overview
The Password Manager App is an Android application developed in Kotlin that allows users to securely manage their passwords. It provides functionalities to add, view, edit, and delete passwords, with all passwords encrypted using the AES encryption algorithm. The app ensures a user-friendly experience with robust error handling to manage any unexpected issues gracefully.

## Features
- **Add Password:** Allows users to add new passwords.
- **View/Edit Password:** Users can view and edit existing passwords.
- **Show List of Passwords on Home Screen:** Displays all saved passwords on the home screen for easy access.
- **Delete Password:** Enables users to delete passwords they no longer need.
- **AES Encryption:** Passwords are securely stored using the AES encryption algorithm.
- **Error Handling:** All errors are managed gracefully to ensure a smooth user experience.

## Screenshots
![Screenshot 1](https://github.com/Nirav186/Password-Manager/assets/59496724/bcbe8ca9-045b-40b8-8d22-38c334cc877a)
![Screenshot 2](https://github.com/Nirav186/Password-Manager/assets/59496724/61c2996b-70f4-46d6-86e7-015cb0dfde55)
![Screenshot 3](https://github.com/Nirav186/Password-Manager/assets/59496724/98cfc778-5196-4fbb-9c6e-41ff49f1727c)

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/password-manager-app.git
    ```
2. Open the project in Android Studio.
3. Build the project and run it on an emulator or a physical device.

## Usage
1. **Adding a Password:**
    - Navigate to the "Add Password" screen.
    - Enter the details and click on the "Save" button.
2. **Viewing/Editing a Password:**
    - Tap on a password from the home screen list.
    - View or edit the details and save changes.
3. **Deleting a Password:**
    - Long press on a password in the list and select the "Delete" option.

## Security
All passwords are encrypted using the AES encryption algorithm to ensure they are stored securely.

## Error Handling
The app includes comprehensive error handling to manage any issues that may arise:
- Network errors
- Data validation errors
- Encryption/Decryption errors
- General exceptions

## Kotlin Multiplatform Support
The app leverages Kotlin libraries to extend Compose Multiplatform support, making it easier to develop and maintain the UI across different platforms.

## Contributing
Contributions are welcome! Please fork the repository and submit pull requests for any features, bug fixes, or improvements.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any queries or support, please contact [nrvrangapariya186@gmail.com](mailto:nrvrangapariya186@gmail.com).

---

Feel free to customize this README file further to suit your project's specific needs!
