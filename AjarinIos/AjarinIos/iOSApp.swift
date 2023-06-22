import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        HomeDatabaseModule()
        HomeRepositoryModule()
        
        LandingUseCasesModule()
        HomeUseCasesModule()
        SearchMentorUseCasesModule()
        MentorProfileUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
