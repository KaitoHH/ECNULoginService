## ECNULoginService
An easy-access to ECNU login database


### Usage
```
import com.networkService.ECNULoginService;
import java.io.IOException;

public class Main {
	private static final String id;
	private static final String password;

	public static void main(String[] args) throws IOException {
		ECNULoginService loginService = new ECNULoginService();
		boolean ret = loginService.login(id, password);
		if (ret) {
			//login success
			System.out.println(loginService.getName());
		} else {
			//login failed
			System.out.println(loginService.getErrorMsg());
		}
	}
}

```
