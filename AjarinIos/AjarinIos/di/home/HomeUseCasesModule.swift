import Foundation
import shared

class HomeUseCasesModule {
    init() {
        @Provider var searchMentorByCourse: SearchMentorByCourse = SearchMentorByCourse()
    }
}
