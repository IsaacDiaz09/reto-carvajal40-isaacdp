<template>
    <div class="cart">
        <table class="table table-sm table-hover">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Item</th>
                    <th>Accion</th>
                </tr>
            </thead>
            <tbody>
                <slot name="body"></slot>
            </tbody>
            <tfoot>
                <slot name="footer"></slot>
            </tfoot>
        </table>
    </div>
</template>