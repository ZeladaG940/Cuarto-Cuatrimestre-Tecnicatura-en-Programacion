import logging

from usuario import Usuario          # asumo que tu clase se llama Usuario
from cursor_del_pool import CursorDelPool

log = logging.getLogger(__name__)


class UsuarioDAO:
    _SELECT = 'SELECT * FROM usuario ORDER BY id_usuario'
    _INSERTAR = 'INSERT INTO usuario (username, password) VALUES (%s, %s)'
    _ACTUALIZAR = 'UPDATE usuario SET username=%s, password=%s WHERE id_usuario=%s'
    _ELIMINAR = 'DELETE FROM usuario WHERE id_usuario=%s'

    @classmethod
    def seleccionar(cls):
        with CursorDelPool() as cursor:
            log.debug('Seleccionando usuario')
            cursor.execute(cls._SELECT)
            registros = cursor.fetchall()
            usuarios = []
            for registro in registros:
                usuarios.append(Usuario(registro[0], registro[1], registro[2]))
            return usuarios

    @classmethod
    def insertar(cls, usuario):
        with CursorDelPool() as cursor:
            log.debug(f'Usuario a insertar: {usuario}')
            valores = (usuario.username, usuario.password)
            cursor.execute(cls._INSERTAR, valores)
            return cursor.rowcount

    @classmethod
    def actualizar(cls, usuario):                     # ✅ renombrado
        with CursorDelPool() as cursor:
            log.debug(f'Usuario a actualizar: {usuario}')
            valores = (usuario.username, usuario.password, usuario.id_usuario)
            cursor.execute(cls._ACTUALIZAR, valores)
            return cursor.rowcount

    @classmethod
    def eliminar(cls, usuario):
        with CursorDelPool() as cursor:
            log.debug(f'Usuario a eliminar: {usuario}')
            valores = (usuario.id_usuario,)           # ✅ coma final
            cursor.execute(cls._ELIMINAR, valores)
            return cursor.rowcount


if __name__ == '__main__':
    logging.basicConfig(level=logging.DEBUG)

    # Eliminar
    usuario = Usuario(id_usuario=1)
    print('Eliminados:', UsuarioDAO.eliminar(usuario))

    # Actualizar
    usuario = Usuario(id_usuario=1, username='', password='369')
    print('Modificados:', UsuarioDAO.actualizar(usuario))

    # Insertar
    usuario = Usuario(username='Kely', password='321')
    print('Insertados:', UsuarioDAO.insertar(usuario))

    # Listar
    for usuario in UsuarioDAO.seleccionar():
        log.debug(usuario)