import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        AppModule()
        HomeDatabaseModule()
        HomeRepositoryModule()
        InboxDatabaseModule()
        InboxRepositoryModule()
        MessageDatabaseModule()
        MessageRepositoryModule()
        
        LandingUseCasesModule()
        HomeUseCasesModule()
        SearchMentorUseCasesModule()
        MentorProfileUseCasesModule()
        HistoryUseCasesModule()
        SessionUseCasesModule()
        InboxUseCasesModule()
        MessageUseCasesModule()
        ApplyAsMentorUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
