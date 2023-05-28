import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        LandingUseCasesModule()
        HomeUseCasesModule()
        SearchMentorUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
