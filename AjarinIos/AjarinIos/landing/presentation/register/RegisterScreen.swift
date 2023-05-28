import SwiftUI

struct RegisterScreen: View {
    @StateObject private var viewModel = IosRegisterViewModel()
    
    var body: some View {
        ZStack {
            switch viewModel.state.currentSection {
            case .createaccount:
                CreateAccount(
                    state: viewModel.state,
                    onEvent: { event in
                        viewModel.onEvent(event: event)
                    }
                )
            case .fillnumber:
                FillNumber(
                    state: viewModel.state,
                    onEvent: { event in
                        viewModel.onEvent(event: event)
                    }
                )
            case .numberverification:
                NumberVerification(
                    state: viewModel.state,
                    onEvent: { event in
                        viewModel.onEvent(event: event)
                    }
                )
            default:
                CreateAccount(
                    state: viewModel.state,
                    onEvent: { event in
                        viewModel.onEvent(event: event)
                    }
                )
            }
        }
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

struct RegisterScreen_Previews: PreviewProvider {
    static var previews: some View {
        RegisterScreen()
    }
}
