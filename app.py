from crypt import methods
from flask import Flask, jsonify, request

app = Flask(__name__)

books = [
    {
        'id': 1,
        'titulo': 'Harry Potter',
        'autor': 'J.K Howling'
    },
    {
        'id': 2,
        'titulo': 'The Lord Of The Rings',
        'autor': 'J.R.R Tolkien'
    }
]

# Gel All
@app.route('/books', methods=['GET'])
def get_books():
    return jsonify(books)

@app.route('/books/<int:id>', methods=['GET'])
def get_books_by_id(id):
    for book in books:
        if book.get('id') == id:
            return jsonify(book)


@app.route('/books/<int:id>', methods=['PUT'])
def save_book_by_id(id):
    book_edited = request.get_json()
    for indice_array, book in enumerate(books):
        if book.get('id') == id:
            books[indice_array].update(book_edited)
            return jsonify(books[indice_array])
        else:
            books.append(book_edited)
            return jsonify(books)

@app.route('/books/<int:id>', methods=['DELETE'])        
def delete_book(id):
    for indice, book in enumerate(books):
        if book.get('id') == id:
            del books[indice]

    return jsonify(books)
    


app.run(port=5000, host='localhost', debug=True)