package org.ldv.appliwickle

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
@Controller
class MainController {
    @GetMapping("/appliwickle")
    fun home(): String {
        return "index"

    }
}