import Foundation
import shared

extension RegisterScreen {
    @MainActor class IosRegisterViewModel: ObservableObject {
        private let viewModel: RegisterViewModel
        
        @Published var state: RegisterState = RegisterState(
            currentSection: .createaccount,
            name: "",
            nameError: nil,
            email: "",
            emailError: nil,
            password: "",
            passwordError: nil,
            isPasswordVisible: false,
            registerEnabled: false,
            number: "",
            numberError: nil,
            sendOtpEnabled: false,
            otp: "",
            otpError: nil,
            verifyOtpEnabled: false,
            otpSendCount: 0,
            otpCountDown: 0,
            verificationId: nil,
            sendOtpError: nil,
            sendOtpLoading: false,
            verifyOtpError: nil,
            verifyOtpLoading: false,
            registerLoading: false,
            registerError: nil,
            registerSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var validationUseCases: ValidationUseCases
            @Inject var register: Register
            
            self.viewModel = RegisterViewModel(
                validationUseCases: validationUseCases,
                register: register,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: RegisterEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.registerSuccess {
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

