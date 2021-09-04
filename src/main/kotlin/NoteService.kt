class NoteService{
    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()

    fun add(note: Note){
        val newNote = if (notes.isNotEmpty()) note.copy(id = notes.last().id + 1)
            else note.copy(id = 1)
        notes += newNote
    }

    fun createComment(comment: Comment){
        val newComment = if (comments.isNotEmpty()) comment.copy(id = comments.last().id + 1)
            else comment.copy(id = 1)
        comments += newComment
    }

    fun edit(noteId: Int, title: String, text: String): String{
        println("\n_____")
        for ((index, note) in notes.withIndex()){
            if(note.id == noteId && !note.deleted){
                notes[index] = note.copy(title = title,
                    text = text)
                return "Пост успешно изменен"
            }
        }
        return "Этот пост удалили"
    }

    fun editComment(commentId: Int, message: String): String{
        println("\n_____")
        for((index, comment) in comments.withIndex()){
            if (comment.id == commentId && !comment.deleted){
                comments[index] = comment.copy(message = message)
                return "Комментарий успешно изменен"
            }
        }
        return "Этот комментарий удалили"
    }

    fun delete(noteId: Int): String{
        println("\n_____")
        for ((index, note) in notes.withIndex()){
            if (note.id == noteId && !note.deleted) {
                notes[index] = note.copy(deleted = true)
                for ((commentIndex, comment) in comments.withIndex()){
                    if(comment.noteId == note.id && !comment.deleted) {
                        comments[commentIndex] = comment.copy(deleted = true)
                    }
                }
                return "Пост и его комментарии успешно удалены"
            }
        }
        return "Этот пост уже был удален"
    }

    fun deleteComment(commentId: Int): String{
        println("\n_____")
        for((index, comment) in comments.withIndex()){
            if (comment.id == commentId && !comment.deleted){
                comments[index] = comment.copy(deleted = true)
                return "Комментарий успешно удален"
            }
        }
        return "Этот комментарию уже был удален"
    }

    fun  getById(noteId: Int): Any{
        println("\n_____")
        for (note in notes){
            if (note.id == noteId) return note

        }
        return "Заметки с id $noteId нету, либо она была удалена"
    }

    fun get(noteIds: Array<Int> = emptyArray(), count: Int = 100, sort: Boolean = false) {
        println("\n______")
        val noteList = mutableListOf<Note>()
        if (noteIds.isNotEmpty()) {
        for (note in notes){
                for (noteId in noteIds) {
                    if (note.id == noteId && !note.deleted) {
                        if (noteList.size >= count) break
                        noteList.add(note)
                    }
                }
            }
        } else {
            for( note in notes) {
                if (noteList.size >= count) break
                if (!note.deleted) noteList.add(note)
            }
        }
        if (sort){
            for ((index, note) in noteList.withIndex()){
                println(noteList[noteList.size - (index + 1)])
            }
        } else {
            for (note in noteList){
                println(note)
            }
        }
    }

    fun getComment(noteId: Int, sort: Boolean = false, count: Int = 100) {
        println("\n_____")
        val commentList = mutableListOf<Comment>()
        for (comment in comments) {
            if (comment.noteId == noteId && !comment.deleted){
                if (commentList.size >= count) break
                commentList.add(comment)
            }
        }
        if (sort){
            for ((index, comment) in commentList.withIndex()){
                println(commentList[commentList.size - (index + 1)])
            }
        } else {
            for (note in commentList) {
                println(note)
            }
        }
    }

    fun restoreComment(commentId: Int): Any {
        println("\n_____")
        for ((index, comment) in comments.withIndex()) {
            if (commentId == comment.id){
                if (!comment.deleted) break
                comments[index] = comment.copy(deleted = false)
                return comments[index]
            }
        }
        return "Этот комментарий не был удален"
    }
}


data class Note(val id:Int = 0,
                val title:String,
                val text:String,
                val deleted: Boolean = false)
data class Comment(val id: Int = 0,
                   val noteId: Int,
                   val replyTo: Int = 0,
                   val message: String,
                   val deleted: Boolean = false)