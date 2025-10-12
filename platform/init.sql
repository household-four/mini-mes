
DROP TABLE IF EXISTS bom_items CASCADE;
DROP TABLE IF EXISTS operations CASCADE;
DROP TABLE IF EXISTS parts CASCADE;

CREATE TABLE parts (
    part_id UUID PRIMARY KEY,
    description TEXT NOT NULL,
    part_type VARCHAR(32) NOT NULL
);

CREATE TABLE operations (
    operation_id UUID PRIMARY KEY,
    part_id UUID NOT NULL,
    workstation_type VARCHAR(64) NOT NULL,
    CONSTRAINT fk_operations_part
        FOREIGN KEY (part_id) REFERENCES parts(part_id)
        ON DELETE CASCADE,
    CONSTRAINT uq_operations_part UNIQUE (part_id)
);

CREATE TABLE bom_items (
    parent_part_id UUID NOT NULL,
    child_part_id UUID NOT NULL,
    CONSTRAINT pk_bom_items PRIMARY KEY (parent_part_id, child_part_id),
    CONSTRAINT fk_bom_parent
        FOREIGN KEY (parent_part_id) REFERENCES parts(part_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_bom_child
        FOREIGN KEY (child_part_id) REFERENCES parts(part_id)
        ON DELETE RESTRICT,
    CONSTRAINT chk_bom_no_self CHECK (parent_part_id <> child_part_id)
);

CREATE INDEX idx_bom_parent ON bom_items(parent_part_id);
CREATE INDEX idx_bom_child ON bom_items(child_part_id);