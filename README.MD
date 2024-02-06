# PayU-Android Sample 

**Please note:** This repository is currently not maintained.
You can use this repository as an example. However please be aware that
we are not going to be handling issues or pull requests on this repository.

**Maintained Project:**
[PayU-EMEA/PayU-Android](https://github.com/PayU-EMEA/PayU-Android) is a project with PayU SDK. 
`app` module represents the example app.

To reflect this state we’ve marked this repository as Archived.

---

To start:
* https://developers.payu.com/pl/restapi.html
* https://developers.payu.com/en/mobile_sdk.html# (prepare your backend and mobile app for rest communication)

To run test app correctly:
* Configure correctly this app please select in `payu.xml` file:
  ```
  <string name="payu_environment"></string>
  ```
   with production or sandbox string

* Add your POS and secret in com.payu.android.front.sdk.demo.repository.PersistentRepository
* Current settings of this app:
  * Env - sandbox
  * POS config - https://developers.payu.com/pl/overview.html#Testing
