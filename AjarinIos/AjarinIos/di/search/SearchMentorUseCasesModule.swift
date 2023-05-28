import Foundation
import shared

class SearchMentorUseCasesModule {
    init() {
        @Provider var searchMentor: SearchMentor = SearchMentor()
    }
}
