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
        HistoryUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
