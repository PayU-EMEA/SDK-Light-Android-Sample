# 1.0.24
* Fixed validate card expire

# 1.0.23
* Updated whitelist PBL

# 1.0.22
* Fixed problem with dependence in card scanner module

# 1.0.21
* Fixed increasing number of requests after clear methods

# 1.0.20
* Added Twisto Slice and Czech to whitelist PBL

# 1.0.19
* Fixed price formatter for Google Pay

# 1.0.18
* Updated whitelist PBL

# 1.0.17
* Added information about card provider when tokenizing a card
* Added support for WARNING_CONTINUE_REDIRECT status

# 1.0.16
* Added support for mail and phone in WebComponent
* Updated to SDK 33
* Added Timeout to soft accept dialog

# 1.0.14 - 1.0.15
* Added new languages: french, lithuanian, romanian, slovenian
* Added possibility to turn on/off scan card date
* Divided SoftAccept USER_CANCEL action into more actions
* Changed formatting strategy for date
* Trim copped number for card number

# 1.0.13
* Fixed issue related to positive button on CVV

# 1.0.12
* Added possibility to clean a card
* Added new languages: spanish, slovakian
* Fixed issue with opening email client form about screen

# 1.0.11
* Added new currency codes to Google Pay
* Added new language: ukrainian
* Sandbox for SoftAccept

# 1.0.10
* Updated CVV dialog

# 1.0.9
* Added marketplace footer library

# 1.0.5 - 1.0.8
* Prepared a possibility to cancel WebView flow in app - menu
* Updated whitelist PBL
* Removed jcenter from dependencies

# 1.0.2 - 1.0.4
Extends SoftAccept Dialog

# 1.0.1
* GP can be disabled when user does not have configure account and does not own a card on GP account
* Fixed issue on some devices with opening Terms & Conditions

# 1.0.0
* Added installments module

# 0.9.6 - 0.9.9
* Extends SoftAccept Dialog
* Bugfixes for SoftAccept dialog

# 0.9.5
* Add support for new PBL
* Add SoftAccept Dialog

# 0.9.4
* removing deprecated libraries
* bugfixes for redirects to mobile apps
* rename continueUrl to continuePaymentUrl

# 0.9.3
* update retrofit
* remove old library for dialogs
* bugfixes for Android Dark Theme
* handling redirects to mobile app from webView

# 0.9.2
* fix on webView for not properly displaying PBL payment
* change progress bar on webView
* update appCompat
* update PBL bank list

# 0.9.1
* fix issue with opening WebView on Huawei devices

# 0.9.0
* Release is available on artifactory.
To use it add this line to gradle repositories maven `{ url "https://payu.jfrog.io/payu/mobile-sdk-gradle-local" }`

* Support for dark mode on Android. To use it add to xml file new flag:
`<bool name="payu_night_mode_enabled">true</bool>`<br /><br />
Sample payu.xml with settings:
    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    <resources xmlns:tools="http://schemas.android.com/tools" tools:ignore="TypographyDashes">
       <!-- Replace placeholder with either production or sandbox -->
       <string name="payu_environment">production</string>
       <!-- Supported values are: auto, polish, english, german, czech or hungarian -->
       <string name="payu_language">auto</string>
       <bool name="payu_save_and_use_card">true</bool>
       <bool name="payu_add_card_option">true</bool>
       <bool name="payu_pbl_option">true</bool>
       <string name="payu_payment_methods_fully_qualified_name">com.payu.android.front.sdk.demo.config.PaymentMethodsProvider</string>
       <string name="payu_style_class_fully_qualified_name">com.payu.android.front.sdk.demo.config.CustomPayUStyle</string>
       <bool name="payu_night_mode_enabled">true</bool>
       <!--NEW BLIK PAYMENT FLAG -->
       <bool name="payu_blik_payment">true</bool>
       <bool name="payu_card_scanner">true</bool>
    </resources>
    ```
* Fix German translations
* Add support for new banks in Germany
* Add new certificate for adding a card
* Enhance card request with additional headers

# 0.8.1
* Fixed bug with comparing colour values while reading style properties

# 0.8.0
* Updated Google Pay API to version 2. It requires to provide merchantName while calling method: GooglePlayService#requestGooglePayCard(Cart cart, String posId, String merchantName)
* Migration to AndroidX
* Improvements during initialization widget (cleanPaymentMethods() can be called right after inflating layout)
* getPaymentMethod() returns non null value
* Fixed bug with providing white colour as a background

# 0.7.2
* when there is an empty pbl list “bank transfer” button will be disabled
* there is additional new optional property for displaying "bank transfer button": `<bool name="payu_pbl_option">true</bool>`
This property is by default true, to hide button please set this property to false

# 0.7.1
* update scanner library (fix issue with dependency not found)
* add masterpass and visa checkout as PBL
* remove WebPayment header

# 0.7.0
* Add Hungarian translations
* Add support for new Currencies for Google Pay ( USD, GBP, RON, HUF, HRK, SEK, NOK, DKK)
* Disable Payment method cannot be selected on Main Payment Widget screen
* Disable Payment method will be move down on Main Payment Widget screen
* Improve validation for adding a new card more user-friendly

# 0.6.2

* Update Czech translations
* Clean BLIK code when changing payment method in Payment Chooser Widget
* Update Rules for verifying card Vendors

# 0.6.1
* Remove unused string
* Fix WebViewClient onReceivedSslError
* Rename Blik Payments Methods

# 0.6.0
* Add display PEX payments in Payment Chooser Widget module
* Add display BLIK payments in Payment Chooser Widget module
* Add component for inputting BLIK code in Payment Chooser Widget module
* Handle PEX payments in Web View Payment module
* Handle BLIK Ambiguity in Payment Chooser Widget module
* Add new module Card Scanner for scanning a card
* Handle card scanner in Payment Chooser Widget module
* Change displaying payment methods in Payment Chooser Widget module
* Change documentation file name for better inform developers about contents in files
* Add Table of contents - information about libraries
* Update Payment Chooser module documentation for handling BLIK, Card Scanner
* Update Web Payments  module documentation for better understanding how to handle Web Payments
* Fix some translations for Czech & German Languages

# 0.5.0
* Change hints for Card Fields
* Change Regulations [PL]
* Success in continueUrl can have query
* Remove SuccessUrl & ErrorUrl from Authorization detail
