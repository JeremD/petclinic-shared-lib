class PseudoStore {

    def data = [
        'ross' : 'Rossi Oddet',
        'gilet' : 'Emmanuel Macron',
        'twitter' : 'Donald Trump',
    ]

    String findNameByPseudo(String pseudo) {
        return data[pseudo]
    }

}
