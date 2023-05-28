import Foundation
import shared

extension LoginScreen {
    @MainActor class IosLoginViewModel: ObservableObject {
        private let viewModel: LoginViewModel
        
        @Published var state: LoginState = LoginState(
            email: "",
            emailError: nil,
            password: "",
            passwordError: nil,
            isPasswordVisible: false,
            loginButtonEnabled: false,
            loginError: nil,
            loginSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var validateEmail: ValidateEmail
            @Inject var validatePassword: ValidatePassword
            
            self.viewModel = LoginViewModel(
                validateEmail: validateEmail,
                validatePassword: validatePassword,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: LoginEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.loginSuccess {
                        let userDefaults = UserDefaults.standard
                        userDefaults.set(true, forKey: "isLogin")
                    }
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
