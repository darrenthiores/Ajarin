import SwiftUI

@main
struct iOSApp: App {
    
    init() {
        AppModule()
        AuthNetworkModule()
        AuthRepositoryModule()
        InboxDatabaseModule()
        InboxRepositoryModule()
        MessageDatabaseModule()
        MessageRepositoryModule()
        ParticipantLocalModule()
        ParticipantRepositoryModule()
        
        ValidationUseCasesModule()
        AuthUseCasesModule()
        UserUseCasesModule()
        MentorUseCasesModule()
        OrderUseCasesModule()
        InboxUseCasesModule()
        MessageUseCasesModule()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
