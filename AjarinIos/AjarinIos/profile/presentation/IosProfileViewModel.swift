import Foundation
import shared

extension ProfileScreen {
    @MainActor class IosProfileViewModel: ObservableObject {
        private let viewModel: ProfileViewModel
        
        @Published var state: ProfileState = ProfileState(
            user: nil,
            isFetchingUser: false,
            isDialogShow: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var getUser: GetUser
            
            self.viewModel = ProfileViewModel(
                getUser: getUser,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: ProfileEvent) {
            if event == .LogOut() {
                logOut()
            }
            
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
        
        private func logOut() {
            let userDefaults = UserDefaults.standard
            userDefaults.set(false, forKey: "isLogin")
        }
    }
}


