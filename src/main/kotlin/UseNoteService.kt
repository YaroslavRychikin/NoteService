fun main(){
    val service = NoteService()
    service.add(Note(title = "Пир", text = "Наемся"))
    service.add(Note(title = "Пора начать сначало", text = "Составляй план и следуй ему"))
    service.add(Note(title = "Что завтра послушать", text = "Слушаем классику"))
    service.createComment(Comment(noteId = 1, message = "Да, да, обязательно"))
    service.createComment(Comment(noteId = 1, message = "Будет вкусно"))
    service.createComment(Comment(noteId = 1, message = "Много чего будет"))
    service.createComment(Comment(noteId = 2, message = "Получаеться"))
    service.createComment(Comment(noteId = 2, message = "Рад прогрессу"))
    service.get()
    service.get(noteIds = arrayOf(1, 2))
    service.get(count = 2, sort = true)
    print(service.getById(noteId = 2))
    service.getComment(noteId = 1)
    println(service.delete(noteId = 2))
    println(service.delete(noteId = 2))
    println(service.deleteComment(commentId = 3))
    println(service.deleteComment(commentId = 4))
    println(service.edit(noteId = 3, title = "Что послушать", text = "Сдушаем джаз"))
    println(service.edit(noteId = 2, title = "Бросай", text = "Капайся"))
    println(service.editComment(commentId = 1, message = "Да, да, обязательно приду"))
    println(service.editComment(commentId = 3, message = "Хорошо, что решил прийти"))
    println(service.restoreComment(commentId = 3))
    println(service.restoreComment(commentId = 1))
}

